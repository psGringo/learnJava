import {Configuration} from "webpack";
import CssMinimizerPlugin from "css-minimizer-webpack-plugin";

export const getOptimization = (isProduction: boolean) => {
    const config: Configuration['optimization'] = {};

    if (isProduction) {
        config.minimize = true;
        config.minimizer = [new CssMinimizerPlugin()]
    }

    return config;
}