// Updated CreateTask.jsx with renamed classNames to avoid conflict
import React, { useState, useRef, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { authFetch } from "../../utils/AuthFetch";
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

    useEffect(() => {
        if (!isExpanded) return;

        const handleClickOutside = (e) => {
            if (
                cardRef.current &&
                !cardRef.current.contains(e.target) &&
                !buttonRef.current.contains(e.target)
            ) {
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
        }, 300);
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
        <div className="popup-task-container">
            <button
                ref={buttonRef}
                className={`popup-add-button glow-${status} ${isExpanded ? 'popup-hidden-button' : ''}`}
                onClick={handleOpen}
            >
                <FontAwesomeIcon icon={faPlus} />
            </button>

            {(isExpanded || isClosing) && (
                <>
                    <div className="popup-overlay" onClick={handleClose}></div>

                    <div
                        ref={cardRef}
                        className={`popup-task-card glow-normal ${isClosing ? 'popup-card-closing' : 'popup-card-opening'}`}
                    >
                        <div className="popup-task-card-header">
                            <h3 className="popup-task-card-title">Create New Task</h3>
                        </div>

                        <div className="popup-task-form">
                            <div className="popup-form-row">
                                <div className="popup-form-group">
                                    <label className="popup-form-label">Assigned To</label>
                                    <input
                                        className="popup-task-input"
                                        value={assignee}
                                        onChange={(e) => setAssignee(e.target.value)}
                                        placeholder="Username"
                                    />
                                </div>

                                <div className="popup-form-group">
                                    <label className="popup-form-label">Status</label>
                                    <select
                                        className={`popup-status-select badge-${status}`}
                                        value={status}
                                        onChange={(e) => setStatus(e.target.value)}
                                    >
                                        <option value="active">Active</option>
                                        <option value="urgent">Urgent</option>
                                    </select>
                                </div>
                            </div>

                            <div className="popup-form-group">
                                <label className="popup-form-label">Description</label>
                                <textarea
                                    className="popup-task-title-input"
                                    value={description}
                                    onChange={(e) => setDescription(e.target.value)}
                                    placeholder="What needs to be done?"
                                    rows={4}
                                />
                            </div>

                            <div className="popup-form-row">
                                <div className="popup-form-group">
                                    <label className="popup-form-label">Start Date</label>
                                    <input
                                        type="date"
                                        className="popup-task-date"
                                        value={startDate}
                                        onChange={(e) => setStartDate(e.target.value)}
                                    />
                                </div>

                                <div className="popup-form-group">
                                    <label className="popup-form-label">Deadline</label>
                                    <input
                                        type="date"
                                        className="popup-task-date"
                                        value={deadline}
                                        onChange={(e) => setDeadline(e.target.value)}
                                    />
                                </div>
                            </div>

                            <button
                                className="popup-create-button"
                                onClick={handleSubmit}
                            >
                                Create Task
                            </button>
                        </div>
                    </div>
                </>
            )}
        </div>
    );
};

export default CreateTask;
