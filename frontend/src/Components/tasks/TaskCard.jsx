import React, { useState } from "react";
import "./TaskCard.css";
import {authFetch} from "../../utils/AuthFetch";

const TaskCard = ({
                      taskId,
                      assignedBy,
                      description,
                      startDate,
                      deadline,
                      status: initialStatus,
                      completed,
                      request
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

    const handleAccept = async ()=>{
       try{
           const res = await authFetch(`/api/v1/tasks/accept/${taskId}`, {
               method: "POST",
           })
           window.location.reload();
       }catch(err){
           console.log(err);
       }
    }
    const handleReject = async ()=>{
       try{
           const res = await authFetch(`/api/v1/tasks/reject/${taskId}`, {
               method: "POST",
           })
           window.location.reload();
       }catch(err){
           console.log(err);
       }
    }



    return (
        <div className={`task-card glow-${done ? "done" : status}`}>
            <div className="task-card-header-content">
                <div className="task-card-header">
                    <div className="task-card-header-top">
                        <span className={`status-badge badge-${done ? "done" : status}`}>
                            Assigned by {assignedBy}
                        </span>
                        {request && (
                            <div className="task-card-circles">
                                <div className="tooltip-container">
                                    <button className="circle green" onClick={handleAccept}></button>
                                    <span className="tooltip-text">Accept Request</span>
                                </div>
                                <div className="tooltip-container">
                                    <button className="circle yellow" onClick={handleReject}></button>
                                    <span className="tooltip-text">Reject Request</span>
                                </div>
                                <div className="tooltip-container">
                                    <button className="circle red" onClick={() => console.log("Reject Request")}></button>
                                    <span className="tooltip-text">Block User</span>
                                </div>
                            </div>
                        )}
                    </div>
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
