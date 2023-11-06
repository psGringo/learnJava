import merge from "webpack-merge";
import webpackConfigBase from "./webpack.config";

const config = merge(webpackConfigBase, {
    plugins: [
        // TO Be continued...
    ]
});

export default config;