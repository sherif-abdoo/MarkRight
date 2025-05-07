import React from "react";
import "./App.css"
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import TestPage from "./Pages/TestPage";
import GlossyCard from "./Components/GlossyCard";
import LoginCard from "./Components/auth/LoginCard";
import SignUpCard from "./Components/auth/SignUpCard";
import LoginPage from "./Pages/LoginPage";
import SignUpPage from "./Pages/SignUpPage";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<LoginPage/>} />
                <Route path="/signup" element={<SignUpPage/>} />
            </Routes>
        </Router>
    );
}

export default App;
