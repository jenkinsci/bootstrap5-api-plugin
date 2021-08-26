module.exports = ctx => {
    return {
        plugins: {
            autoprefixer: {
                cascade: false
            },
            rtlcss: ctx.env === 'RTL' ? {} : false
        }
    }
}
