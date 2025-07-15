export default {
    data() {
        return {
            form: {
                designRes: null // 默认值设为 null
            },
            yes_or_no: [
                {value: 'yes', label: '是'},
                {value: 'no', label: '否'}
            ]
        };
    },
    methods: {
        handleRadioClick(value) {
            if (this.form.designRes === value) {
                this.form.designRes = null; // 如果再次点击已选中的选项，则取消选择
            }
        }
    }
};
