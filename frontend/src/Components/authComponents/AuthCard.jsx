import React from "react";
import "./AuthCard.css";

const AuthCard = ({ title, children,
                      footerText, footerLinkText,
                      footerLinkHref, buttonText,
                      forgotPasswordLink , handleOnClick }) => {
    return (
        <div className="auth-card">
            <h2 className="auth-title">{title}</h2>

            <div className="auth-fields">
                {children}
            </div>

            <div className="auth-footer">
                <span>{footerText} <a href={footerLinkHref} className="auth-link">{footerLinkText}</a></span>
                {forgotPasswordLink && (
                    <div className="forgot-password">
                        <a href="#forgot" className="forgot-link">Forgot Password?</a>
                    </div>
                )}
            </div>

            <div className="button-container">
                <button className="auth-button" onClick={handleOnClick}>{buttonText || "Log In"}</button>
            </div>
        </div>
    );
};

export default AuthCard;