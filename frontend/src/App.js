import React from "react";
import "./App.css"
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./Pages/authPages/LoginPage";
import SignUpPage from "./Pages/authPages/SignUpPage";
import TestPage from "./Pages/TestPage";
import TasksContainer from "./Components/tasks/TasksContainer";
import CreateTask from "./Components/shared/CreateTask";
import TasksPage from "./Pages/TasksPage";
import TasksRequestsPage from "./Pages/TasksRequestsPage";
import ProfilePage from "./Pages/ProfilePage";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<LoginPage/>} />
                <Route path="/signup" element={<SignUpPage/>} />
                <Route path="/tasks" element={<TasksPage/>} />
                <Route path="/requests" element={<TasksRequestsPage/>} />
                <Route path="/profile" element={<ProfilePage/>} />
                <Route path="/test" element={<TestPage/>} />
            </Routes>
        </Router>
    );
}

export default App;
