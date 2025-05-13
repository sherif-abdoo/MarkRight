import React from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import LoginPage from "./Pages/authPages/LoginPage";
import SignUpPage from "./Pages/authPages/SignUpPage";
import TasksPage from "./Pages/Task Pages/TasksPage";
import TasksRequestsPage from "./Pages/Task Pages/TasksRequestsPage";
import ProfilePage from "./Pages/ProfilePage";
import TestPage from "./Pages/Task Pages/TestPage";

import MainLayout from "./layouts/MainLayout";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/signup" element={<SignUpPage />} />
                <Route path="/" element={<MainLayout><TasksPage /></MainLayout>} />
                <Route path="/requests" element={<MainLayout><TasksRequestsPage /></MainLayout>} />
                <Route path="/profile/:username" element={<MainLayout><ProfilePage /></MainLayout>} />
            </Routes>
        </Router>
    );
}

export default App;
