const { defineConfig } = require("@vue/cli-service")
const path = require("path");

module.exports = defineConfig({
  publicPath: "/vue",
  outputDir: path.resolve(__dirname, "../src/main/resources/static/vue"),
  indexPath: "../../templates/vue/index.html",
  devServer: {
    proxy: "http://localhost:8787"
  },
  configureWebpack: {
    resolve: {
      alias: {
        "@assets": path.resolve(__dirname, "src/assets"),
        "@components": path.resolve(__dirname, "src/components"),
        "@router": path.resolve(__dirname, "src/router"),
        "@store": path.resolve(__dirname, "src/store")
      },
    },
  },
  transpileDependencies: true,
  lintOnSave: false
});
