import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import TestPage from "./Pages/TestPage";
import GlossyCard from "./Components/GlossyCard";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<GlossyCard />} />
            </Routes>
        </Router>
    );
}

export default App;
