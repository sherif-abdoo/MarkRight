import { useEffect, useState } from "react";
import TaskCard from "./TaskCard";
import { authFetch } from "../utils/AuthFetch"; // adjust path if needed
import "./TasksContainer.css";

const TasksContainer = () => {
    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchTasks = async () => {
            try {
                const res = await authFetch("/api/v1/tasks/get_tasks");
                setTasks(res.data.tasks);
            } catch (err) {
                setError("Failed to fetch tasks");
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchTasks();
    }, []);

    // helper to calculate date diff in days
    const getDayDifference = (start, end) => {
        const startDate = new Date(start);
        const endDate = new Date(end);
        const diffTime = Math.abs(endDate - startDate);
        return Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    };

    return (
        <div className="tasks-page">


            {loading && <p className="loading-text">Loading tasks...</p>}
            {error && <p className="error-text">{error}</p>}

            <div className="tasks-container">
                <div className="tasks">
                    {tasks.map((task, idx) => {
                        const dayDiff = getDayDifference(task.startDate, task.endDate);
                        const status = task.completed
                            ? "done"
                            : dayDiff === 1
                                ? "urgent"
                                : "active";

                        return (
                            <TaskCard
                                key={idx}
                                assignedBy={task.creatorUsername}
                                description={task.description}
                                startDate={task.startDate}
                                deadline={task.endDate}
                                status={status}
                            />
                        );
                    })}
                </div>
            </div>
        </div>
    );
};

export default TasksContainer;
