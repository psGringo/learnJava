import {RuleSetRule} from "webpack";
import path from "path";

const styleLoader = (test, extraLoader?) => {
    const use: (RuleSetRule | string)[] = ['style-loader'];

    if (extraLoader) {
        use.push({
            loader: 'css-loader',
            options: {
                modules: {
                    localIdentName: '[local]__[hash:base64:5]',
                    exportLocalsConvention: 'camelCase',
                }
            }
        });

        use.push(extraLoader)
    }

    return {
        test,
        use
    }

}

const getRules = (isProduction: boolean): RuleSetRule[] =>  [
    {
        test: /\.(svg|ico|png|gif|jpg)$/,
        use: [
            {
                loader: 'file-loader',
                options: {
                    name: 'images/[hash:base64:5].[ext]'
                }
            }
        ]
    },
    {
        test: /\.(eot|ttf|woff|woff2)$/,
        use: [
            {
                loader: 'file-loader',
                options: {
                    name: 'fonts/[hash:base64:5].[ext]'
                }
            }
        ]
    },
    {
        enforce: 'pre',
        test: /\.css$/,
        exclude: /node_modules/,
        loader: 'typed-css-modules-loader',
        options: {
            noEmit: isProduction
        }
    },
    styleLoader(/\.css$/),
    styleLoader(/\.less$/, 'less-loader'),
    {
        test: /\.(ts|tsx)$/,
        loader: 'ts-loader',
        exclude: /node_modules/,
        options: {
            configFile: path.resolve(__dirname, '../tsconfig.json')
        }
    },
    {
        test:/\.(?:js|mjs|cjs)$/,
        exclude: /node_modules/,
        use: {
            loader: 'babel-loader',
            options: {
                presets: [['@babel/preset-env', {targets: 'defaults'}]]
            }
        }
    }
]

export default getRules;