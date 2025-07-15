<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8" >
        <el-card class="update-log">
          <div class="body">
            <img src="@/assets/images/logo1.png" alt="logo" style="width:100%"
            />
          </div>
        </el-card>
      </el-col>
      <el-col :sm="12" :lg="12" style="margin-top: 40px">
        <h1 STYLE="accent-color: black; font-size: 46px;text-align: center ;font-weight: bold" >中国轻工业南宁设计工程有限公司</h1>
        <h1 STYLE="accent-color: black; font-size: 46px;text-align: center ;font-weight: bold" >项目管理系统</h1>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="100" :sm="100" :md="100" :lg="100" span="20" >
        <el-card class="update-log" >
          <div class="body" style="width: 1200px;height: 500px">
            <img src="@/assets/images/indexPng.jpg" alt="gate" style="width:100%;height: 100%" />
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <div class="el-index-footer">
        <span style="color: black">© 2024 中国轻工业南宁设计工程有限公司 .All Rights Reserved    系统开发：EIM中心</span>
      </div>
    </el-row>
  </div>
</template>

<script setup name="Index">
const version = ref('3.8.7')

function goTarget(url) {
  window.open(url, '__blank')
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}

.el-index-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
</style>

