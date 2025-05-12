import React, { useEffect, useState } from "react";
import "./TestPage.css"
import { authFetch } from "../utils/AuthFetch";
import TaskCard from "../Components/TaskCard";
import TasksContainer from "../Components/TasksContainer";
import CreateTask from "../Components/CreateTask";

export default function TestPage() {
    return(
        <div className="tasks-page">
            <CreateTask/>
            <TasksContainer/>
        </div>
    )
}
