// src/components/VerticalNavbar.jsx
import React from "react";
import "./VerticalNavbar.css";
import CreateTask from "./CreateTask";
import NotificationsIcon from "./NotificationsIcon";
import ProfileIcon from "./ProfileIcon";


const VerticalNavbar = ({ onAddTask }) => {
    return (

        <div className="vertical-navbar">
            <div className="nav-icon-button"><CreateTask /></div>
            <div className="nav-icon-button"><NotificationsIcon/></div>
            <div className="nav-icon-button"><ProfileIcon/></div>
        </div>

    );
};

export default VerticalNavbar;
