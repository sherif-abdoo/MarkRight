import { useEffect, useState } from "react";
import TaskCard from "./TaskCard";
import { authFetch } from "../../utils/AuthFetch";
import "./TasksContainer.css";

const TasksContainer = ({all}) => {
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
        const fetchPending = async () => {
            try {
                const res = await authFetch("/api/v1/tasks/pending");
                setTasks(res.data.tasks);
            }catch(err) {
                setError("Failed to fetch tasks");
                console.error(err);
            }
            finally {
                setLoading(false);
            }
        }
        if(all == true){
            fetchTasks();
        }else{
            console.log(all);
            fetchPending();
        }
    }, []);



    return (
        <div className="tasks-page">


            {loading && <p className="loading-text">Loading tasks...</p>}
            {error && <p className="error-text">{error}</p>}

            {tasks.length > 0 ?
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
                                    request ={!all}
                                />
                            );
                        })}
                    </div>
                </div> :
                <div className="tasks-empty-text">
                    <h1>No Tasks Yet</h1>
                </div>
            }
        </div>
    );
};

export default TasksContainer;
