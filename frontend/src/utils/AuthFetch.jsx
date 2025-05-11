import api from './api';

export const authFetch = async (url, options = {}, retry = true) => {
    try {

        const response = await api({
            url,
            method: options.method || 'GET',
            data: options.body || null,
            headers: {
                'Content-Type': options.headers?.['Content-Type'] || 'application/json',
                ...options.headers,
            },
        });

        return response.data;
    } catch (err) {
        if (err.response?.status === 401 && retry) {

            try {
                const refreshRes = await api.post("/refresh-token");

                if (refreshRes.status === 200) {
                    return authFetch(url, options, false);
                } else {
                }
            } catch (refreshErr) {
                console.error(`[authFetch] ❌ Refresh error`, refreshErr);
            }

            throw new Error("Session expired. Please log in again.");
        }

        throw err;
    }
};
