import React, { useEffect, useState } from "react";
import { authFetch } from "../utils/AuthFetch";

export default function TestPage() {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchPublicData = async () => {
            try {
                const res = await authFetch("/public"); // your backend URL is already set in axios baseURL
                setData(res);
            } catch (err) {
                console.error(err);
                setError("Failed to load public data");
            }
        };

        fetchPublicData();
    }, []);

    if (error) return <p>{error}</p>;
    if (!data) return <p>Loading...</p>;

    return (
        <div>
            <h1>Public API Response</h1>
            <pre>{JSON.stringify(data, null, 2)}</pre>
        </div>
    );
}
