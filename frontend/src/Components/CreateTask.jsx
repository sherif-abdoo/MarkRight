// CreateTask.jsx
import React, { useState, useRef, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus, faTimes } from "@fortawesome/free-solid-svg-icons";
import { authFetch } from "../utils/AuthFetch";
import "./CreateTask.css";

const CreateTask = ({ onTaskCreated }) => {
    const [isExpanded, setIsExpanded] = useState(false);
    const [isClosing, setIsClosing] = useState(false);
    const [description, setDescription] = useState("");
    const [startDate, setStartDate] = useState("");
    const [deadline, setDeadline] = useState("");
    const [assignee, setAssignee] = useState("");
    const [status, setStatus] = useState("active");

    const cardRef = useRef(null);
    const buttonRef = useRef(null);

    // Handle click outside to close
    useEffect(() => {
        if (!isExpanded) return;

        const handleClickOutside = (e) => {
            if (cardRef.current &&
                !cardRef.current.contains(e.target) &&
                !buttonRef.current.contains(e.target)) {
                handleClose();
            }
        };

        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, [isExpanded]);

    const handleOpen = () => {
        setIsExpanded(true);
        setIsClosing(false);
    };

    const handleClose = () => {
        setIsClosing(true);
        setTimeout(() => {
            setIsExpanded(false);
            setIsClosing(false);
        }, 300); // Match this with CSS transition duration
    };

    const handleSubmit = async () => {
        if (!description || !startDate || !deadline || !assignee || !status) {
            alert("Please fill in all fields before creating a task.");
            return;
        }

        const payload = {
            assigneeUsername: assignee,
            status: status.toUpperCase(),
            description,
            startDate,
            endDate: deadline,
        };

        try {
            await authFetch("/api/v1/tasks/create", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });

            if (typeof onTaskCreated === "function") {
                onTaskCreated();
            }

            handleClose();

            setDescription("");
            setStartDate("");
            setDeadline("");
            setAssignee("");
            setStatus("active");
        } catch (error) {
            alert("Failed to create task. Try again.");
            console.error("Error creating task:", error);
        }
    };

    return (
        <div className="create-task-container">
            {/* Plus Button */}
            <button
                ref={buttonRef}
                className={`add-task-button glow-${status} ${isExpanded ? 'hidden-button' : ''}`}
                onClick={handleOpen}
            >
                <FontAwesomeIcon icon={faPlus} />
            </button>

            {/* Expanded Card */}
            {(isExpanded || isClosing) && (
                <div
                    ref={cardRef}
                    className={`task-card glow-${status} ${isClosing ? 'card-closing' : 'card-opening'}`}
                >
                    <div className="task-card-header">
                        <h3 className="task-card-title">Create New Task</h3>

                    </div>

                    <div className="task-form">
                        {/* Assignee & Status */}
                        <div className="form-row">
                            <div className="form-group">
                                <label className="form-label">Assigned To</label>
                                <input
                                    className="task-input"
                                    value={assignee}
                                    onChange={(e) => setAssignee(e.target.value)}
                                    placeholder="Username"
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label">Status</label>
                                <select
                                    className={`status-select badge-${status}`}
                                    value={status}
                                    onChange={(e) => setStatus(e.target.value)}
                                >
                                    <option value="active">Active</option>
                                    <option value="urgent">Urgent</option>
                                </select>
                            </div>
                        </div>

                        {/* Description */}
                        <div className="form-group">
                            <label className="form-label">Description</label>
                            <textarea
                                className="task-title-input"
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                                placeholder="What needs to be done?"
                                rows={4}
                            />
                        </div>

                        {/* Dates */}
                        <div className="form-row">
                            <div className="form-group">
                                <label className="form-label">Start Date</label>
                                <input
                                    type="date"
                                    className="task-date"
                                    value={startDate}
                                    onChange={(e) => setStartDate(e.target.value)}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label">Deadline</label>
                                <input
                                    type="date"
                                    className="task-date"
                                    value={deadline}
                                    onChange={(e) => setDeadline(e.target.value)}
                                />
                            </div>
                        </div>

                        {/* Submit Button */}
                        <button
                            className={`create-button `}
                            onClick={handleSubmit}
                        >
                            Create Task
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default CreateTask;