import React from "react";
import AuthCard from "./AuthCard";

function AuthPage() {
    return (
        <>
            <AuthCard
                title="Login"
                footerText="Don't have an account?"
                footerLinkText="Sign Up"
                footerLinkHref="/signup"
                buttonText="Log In"
                forgotPasswordLink={true}
            >
                <input
                    type="text"
                    placeholder="Username"
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
