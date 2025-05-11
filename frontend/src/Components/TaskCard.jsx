import React, { useState } from "react";
import "./TaskCard.css";
import {authFetch} from "../utils/AuthFetch";

const TaskCard = ({
                      taskId,
                      assignedBy,
                      description,
                      startDate,
                      deadline,
                      status: initialStatus,
                      completed
                  }) => {
    const [expanded, setExpanded] = useState(false);
    const [done, setDone] = useState(completed);
    const [status, setStatus] = useState(initialStatus);
    const MAX_LENGTH = 50;

    const shortDescription =
        description.length > MAX_LENGTH && !expanded
            ? description.substring(0, MAX_LENGTH) + "..."
            : description;

    const doneTask = async ()=>{
        const res = await authFetch(`/api/v1/tasks/done/${taskId}`, {
            method: "POST",
        });
        setDone(!done);
    }

    return (
        <div className={`task-card glow-${done ? "done" : status}`}>
            <div className="task-card-header-content">
                <div className="task-card-header">
                <span className={`status-badge badge-${done ? "done" : status}`}>
                  Assigned by {assignedBy}
                </span>
                    <div className={`status-badge badge-${done ? "done" : status}`} onClick={doneTask}>
                        {done ? "DONE!" : status.toUpperCase()}
                    </div>
                </div>
                <h2
                    className={`task-title left-align ${done ? "line-through" : "text-" + status}`}
                    onClick={() => {
                        if (description.length > MAX_LENGTH) {
                            setExpanded(prev => !prev);
                        }
                    }}
                    style={{ cursor: expanded ? "pointer" : "default" }}
                    title="Click to expand/collapse"
                >
                    {shortDescription}
                </h2>
            </div>


            <div className="task-fields"></div>

            <div className="task-footer">
                <p className={"task-date"}>Start : {startDate}</p>
                <p className={"task-date"}>Deadline : {deadline}</p>
            </div>
        </div>
    );
};

export default TaskCard;
