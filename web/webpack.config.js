const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  context: path.resolve(__dirname, 'src'),
  entry: [ '@babel/polyfill', './index.js'],
  devtool: 'source-map',
  devServer : {
    historyApiFallback: true
  },
  cache: true,
  mode: 'development',
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'dist'),
    publicPath: '/',
  },
  plugins: [new HtmlWebpackPlugin({
      template: "./index.html"
  })],
  module: {
    rules: [
        // js jsx
        { 
            test: /\.(js|jsx)$/,
            exclude: /node_modules/,
            use: [{
                loader: 'babel-loader',
                options: {
                    presets: ["@babel/preset-env", "@babel/preset-react"]
                }
            }]
        },
        // sass
        {
            test: /\.s[ac]ss$/i,
            use: [
              // Creates `style` nodes from JS strings
              'style-loader',
              // Translates CSS into CommonJS
              'css-loader',
              // Compiles Sass to CSS
              'sass-loader',
            ],
        },
        // svg
        {
            test: /\.svg$/,
            loader: 'svg-inline-loader'
        },
        // html
        {
            test: /\.html$/,
            use: [
              {
                loader: "html-loader"
              }
            ]
        },
        // css
        {
          test: /\.css$/i,
          use: ['style-loader', 'css-loader'],
        }
    ]
  },
};