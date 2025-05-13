import React from "react";
import VerticalNavbar from "../Components/shared/VerticalNavbar";


export default function MainLayout({ children }) {
    return (
        <div style={{ display: "flex" }}>
            <VerticalNavbar />
            <div style={{ flex: 1 }}>{children}</div>
        </div>
    );
}
