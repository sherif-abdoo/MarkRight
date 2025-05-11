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
                const res = await authFetch("/api/v1/tasks/all");
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


    return (
        <div className="tasks-page">


            {loading && <p className="loading-text">Loading tasks...</p>}
            {error && <p className="error-text">{error}</p>}

            <div className="tasks-container">
                <div className="tasks">
                    {tasks.map((task, idx) => {
                        return (
                            <TaskCard
                                key={idx}
                                taskId={task.taskId}
                                assignedBy={task.creatorUsername}
                                description={task.description}
                                startDate={task.startDate}
                                deadline={task.endDate}
                                status={task.status.toLowerCase()}
                                completed={task.completed}
                            />
                        );
                    })}
                </div>
            </div>
        </div>
    );
};

export default TasksContainer;
