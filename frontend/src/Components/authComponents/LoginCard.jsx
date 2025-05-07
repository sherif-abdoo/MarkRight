import React from "react";
import AuthCard from "./AuthCard";
import {useNavigate} from "react-router-dom";
import sanitize from "../../utils/Sanitize";
function AuthPage() {
    const [username, setUsername] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [error, setError] = React.useState("");
    const navigate = useNavigate();

    const handleLogin = async (e)=>{
        e.preventDefault();
        setError("");

        try{
            if(!username || !password){
                setError("all fields are required");
            }
            setUsername(sanitize(username));
            setPassword(sanitize(password));
            const res = await fetch(process.env.REACT_APP_BACKEND_URL +     '/login', {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password })
            });
            if(!res.ok){
                const message = await res.json();
                setError(message.data.error);
                console.log(error);
            }
            else navigate("/");
        }catch(err){
            console.log(err);
        }
    }

    return (
        <>
            <AuthCard
                title="Login"
                footerText="Don't have an account?"
                footerLinkText="Sign Up"
                footerLinkHref="/signup"
                buttonText="Log In"
                forgotPasswordLink={true}
                handleOnClick={handleLogin}
            >
                <input
                    type="text"
                    placeholder="Username"
                    className="auth-input"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Password"
                    className="auth-input"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </AuthCard>
        </>
    );
}

export default AuthPage;
