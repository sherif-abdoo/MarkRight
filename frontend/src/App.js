import React from "react";
import "./App.css"
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./Pages/authPages/LoginPage";
import SignUpPage from "./Pages/authPages/SignUpPage";
import TestPage from "./Pages/TestPage";
import TasksContainer from "./Components/TasksContainer";
import CreateTask from "./Components/CreateTask";
import TasksPage from "./Pages/TasksPage";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<LoginPage/>} />
                <Route path="/signup" element={<SignUpPage/>} />
                <Route path="/tasks" element={<TasksPage/>} />
                <Route path="/test" element={<CreateTask/>} />
            </Routes>
        </Router>
    );
}

export default App;
