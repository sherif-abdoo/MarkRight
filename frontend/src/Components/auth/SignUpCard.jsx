import React from "react";
import AuthCard from "./AuthCard";

function AuthPage() {
    return (
        <>
            <AuthCard
                title="Sign Up"
                footerText="Already have an account?"
                footerLinkText="Log In"
                footerLinkHref="/login"
                buttonText="Sign Up"
                forgotPasswordLink={false}
            >
                <input
                    type="text"
                    placeholder="Username"
                    className="auth-input"
                />
                <input
                    type="text"
                    placeholder="Email"
                    className="auth-input"
                />
                <input
                    type="password"
                    placeholder="Password"
                    className="auth-input"
                />
            </AuthCard>
        </>
    );
}

export default AuthPage;
