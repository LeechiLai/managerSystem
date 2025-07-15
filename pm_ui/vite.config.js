import {defineConfig, loadEnv} from 'vite'
import path from 'path'
import createVitePlugins from './vite/plugins'

// https://vitejs.dev/config/
export default defineConfig(({mode, command}) => {
    const env = loadEnv(mode, process.cwd())
    const {VITE_APP_ENV} = env
    return {
        // 部署生产环境和开发环境下的URL。
        // 默认情况下，vite 会假设你的应用是被部署在一个域名的根路径上
        // 例如 https://www.ruoyi.vip/。如果应用被部署在一个子路径上，你就需要用这个选项指定这个子路径。例如，如果你的应用被部署在 https://www.ruoyi.vip/admin/，则设置 baseUrl 为 /admin/。
        base: VITE_APP_ENV === 'production' ? '/' : '/',

        plugins: createVitePlugins(env, command === 'build'),
        resolve: {
            // https://cn.vitejs.dev/config/#resolve-alias
            alias: {
                // 设置路径
                '~': path.resolve(__dirname, './'),
                // 设置别名
                '@': path.resolve(__dirname, './src')
                //对写法：你的路径->assets/文件，自动翻译
                //错写法：你的路径->assets/你的路径/文件，而实际上，打包的时候dev的路径是要删掉的作为对应的


            },
            // https://cn.vitejs.dev/config/#resolve-extensions
            extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
        },
        // vite 相关配置
        server: {
            //port: 80,//部署用
            port: 801,//开发的中间虚拟服务器用，后面会被nginx代替,打包的时候必须注释掉这一句，开放上一句以启用80端口
            host: true,
            open: true,

            proxy: {//此设置仅仅在开发环境有效，就是中间开发服务器，部署时该设置全部无效，必须用nginx代替，且必须在nginx写相对应的代理转发设置
                // https://cn.vitejs.dev/config/#server-proxy
                //开发环境的配置，由.env.development文件指定了'/dev-api',遇到这个路径要找相应的代理服务器
                '/dev-api': {
                    target: 'http://localhost:8080',
                    changeOrigin: true,
                    rewrite: (p) => p.replace(/^\/dev-api/, '')
                },
                // //部署环境的配置，由.env.production文件指定了'/prod-api',遇到这个路径要找相应的代理服务器
                // '/prod-api': {
                //     //target: 'http://localhost:8080',//开发用
                //     target: 'http://10.105.0.90:8080',//部署用
                //     // target: 'https://api.wzs.pub/mock/13',
                //     changeOrigin: true,
                //     rewrite: (p) => p.replace(/^\/dev-api/, '')
                // }
            }
        },
        //fix:error:stdin>:7356:1: warning: "@charset" must be the first rule in the file
        css:
            {
                postcss: {
                    plugins: [
                        {
                            postcssPlugin: 'internal:charset-removal',
                            AtRule: {
                                charset: (atRule) => {
                                    if (atRule.name === 'charset') {
                                        atRule.remove();
                                    }
                                }
                            }
                        }
                    ]
                }
            }
    }
})
