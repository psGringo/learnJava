import {Configuration as DevServerConfiguration} from "webpack-dev-server";
import {Configuration, EnvironmentPlugin} from 'webpack'
import * as path from "path";
import {getOptimization} from "./getOptimization";
import MiniCssExtractPlugin from "mini-css-extract-plugin";
import getRules from "./getRules";
import HtmlWebpackPlugin from "html-webpack-plugin";

const APP_VERSION = process.env.PATH_VERSION || '00.000';
const isProduction = process.argv.some((arg) => arg === '-p' || arg === '--production') || process.env.NODE_ENV === 'production';

const hash = isProduction ? '.[contenthash]' : '';

const filename = (ext: string, withHash = false) => `${ext}/[name]${withHash ? hash : ''}`;

const sourceMapFilename = () => 'sourceMaps/[file].map';

const devServerConfig: DevServerConfiguration = {
    port: 2077,
    static: {
        directory: path.join(__dirname, 'out'),
    }
};

const webpackConfigBase: Configuration = {
    devServer: devServerConfig,
    mode: isProduction ? 'production' : 'development',
    devtool: isProduction ? 'hidden-source-map' : 'source-map',
    optimization: getOptimization(isProduction),
    target: ['web', 'es5'],
    entry: path.resolve(__dirname, '../src/index.tsx'),
    module: {
        rules: getRules(isProduction)
    },
    resolve: {
        extensions: ['.js', '.ts', '.tsx', '.css', '.less', '.json', '.png'],
        modules: [path.resolve(__dirname, 'node_modules'), 'node_modules'],
        alias: {
            '@': path.resolve(__dirname, '../src'),
            '@api': path.resolve(__dirname, '../openapi')
        }
    },

    output: {
        clean: true,
        path: path.resolve(__dirname, '../out'),
        filename: 'index.js', //filename('js'),
        chunkFilename: filename('js', true),
        sourceMapFilename: sourceMapFilename(),
        libraryTarget: 'umd',
        // public path influence on index.html <script src = 'publicPath'/filename> </script>
        // publicPath: `/draft/${APP_VERSION}`

    },

    resolveLoader: {
        modules: [path.resolve(__dirname, 'node_modules'), 'node_modules']
    },

    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, '../src/index.html'),
            minify: {
                collapseWhitespace: isProduction
            }
        }),
        new MiniCssExtractPlugin(),
        new EnvironmentPlugin({
            PATH_VERSION: APP_VERSION
        })
    ]
}

// webpackConfigBase['devServer'] = {
//     port: 3003,
//     static: {
//         directory: path.join(__dirname, 'out'),
//         publicPath: 'src2/'
//     }
// }

export default webpackConfigBase;