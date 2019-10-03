const path = require("path");
module.exports = {
    devServer: {
        compress: true,
        inline: true,
        port: '8080',
        allowedHosts: [
            '*'
        ],
        disableHostCheck: true
    },
    chainWebpack: config => {
        config
            .entry("app")
            .clear()
            .add("./src/frontend/main.ts")
            .end();
        config.resolve.alias
            .set("@", path.join(__dirname, "./src/frontend/"))
        config.module
            .rule("i18n")
            .resourceQuery(/blockType=i18n/)
            .type('javascript/auto')
            .use("i18n")
            .loader("@kazupon/vue-i18n-loader")
            .end();
    },
};
