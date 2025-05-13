import React from "react";
import "./VerticalNavbar.css";
import CreateTask from "./CreateTask";
import NotificationsIcon from "./NotificationsIcon";
import ProfileIcon from "./ProfileIcon";
import {useLocation} from "react-router-dom";
import TasksIcon from "./TasksIcon";


const VerticalNavbar = ({ onAddTask }) => {
    const location = useLocation();
    const isHomePage = location.pathname === "/";
    return (
        <div className="vertical-navbar">
            <div className="nav-icon-button">
                {isHomePage ? <CreateTask /> : <TasksIcon />}
            </div>
            <div className="nav-icon-button"><NotificationsIcon/></div>
            <div className="nav-icon-button"><ProfileIcon/></div>
        </div>

    );
};

export default VerticalNavbar;
