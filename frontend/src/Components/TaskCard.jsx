import React, { useState } from "react";
import "./TaskCard.css";

const TaskCard = ({
                      assignedBy = "me",
                      description = "Finish task edit endpoint",
                      startDate = "2025-5-5",
                      deadline = "2025-5-7",
                      status,
                      completed = status === "done"
                  }) => {
    const [expanded, setExpanded] = useState(false);
    const MAX_LENGTH = 130;

    const shortDescription =
        description.length > MAX_LENGTH && !expanded
            ? description.substring(0, MAX_LENGTH) + "..."
            : description;

    return (
        <div className={`task-card glow-${status}`}>
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
                onClick={() => setExpanded(description.length > MAX_LENGTH && !expanded)}
                style={{ cursor: expanded ? "pointer" : "default" }}
                title="Click to expand/collapse"
            >
                {shortDescription}
            </h2>

            <div className="task-fields"></div>

            <div className="task-footer">
                <p className={"task-date"}>Start : {startDate}</p>
                <p className={"task-date"}>Deadline : {deadline}</p>
            </div>
        </div>
    );
};

export default TaskCard;
