const config = {
    development: {
        baseURL: 'http://localhost:8089',
    },
    production: {
        baseURL: 'http://1.94.98.78:8089',
    },
};

export const getConfig = () => {
    const env = 'development';
    // const env = 'production';
    return config[env];
};
