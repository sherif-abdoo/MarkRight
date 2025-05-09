import React, { useState } from "react";
import "./TaskCard.css";

const TaskCard = ({
                      assignedBy,
                      description,
                      startDate,
                      deadline,
                      status,
                      completed = status === "done"
                  }) => {
    const [expanded, setExpanded] = useState(false);
    const MAX_LENGTH = 50;

    const shortDescription =
        description.length > MAX_LENGTH && !expanded
            ? description.substring(0, MAX_LENGTH) + "..."
            : description;

    return (
        <div className={`task-card glow-${status}`}>
            <div className="task-card-header-content">
                <div className="task-card-header">
                <span className={`status-badge badge-${status}`}>
                  Assigned by {assignedBy}
                </span>
                    <div className={`status-badge badge-${status}`}>
                        {status.toUpperCase()}
                    </div>
                </div>
                <h2
                    className={`task-title left-align ${completed ? "line-through text-green" : "text-" + status}`}
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
