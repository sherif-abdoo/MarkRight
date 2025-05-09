import React, { useEffect, useState } from "react";
import "./TestPage.css"
import { authFetch } from "../utils/AuthFetch";
import TaskCard from "../Components/TaskCard";
import TasksContainer from "../Components/TasksContainer";

export default function TestPage() {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);

    // useEffect(() => {
    //     const fetchPublicData = async () => {
    //         try {
    //             const res = await authFetch("/public"); // your backend URL is already set in axios baseURL
    //             setData(res);
    //         } catch (err) {
    //             console.error(err);
    //             setError("Failed to load public data");
    //         }
    //     };
    //
    //     fetchPublicData();
    // }, []);

    // if (error) return <p>{error}</p>;
    // if (!data) return <p>Loading...</p>;

    return (
        <div className="container">
            <TasksContainer/>
        </div>
    );
}
