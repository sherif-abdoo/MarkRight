import React, {useState} from "react";
import AuthCard from "./AuthCard";
import sanitize from "../../utils/Sanitize";


function AuthPage() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [response, setResponse] = useState("");
    const handleSignUp = async (e) =>{
        e.preventDefault();
        setResponse("");
        try{
            if(!username || !password || !email){
                console.log("all fields are required");
                return;
            }
            setUsername(sanitize(username));
            setPassword(sanitize(password));
            setEmail(sanitize(email));
            const res    = await fetch(process.env.REACT_APP_BACKEND_URL + "/sign_up", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password, email })
            });
            const json = await res.json();
            if(!res.ok){
                setResponse(json.data.error);
                console.log(response);
            }else{
                setResponse(json.data.message);
                console.log(response);
            }
        }
        catch(err){
            console.log(err);
        }

    }
    return (
        <>
            <AuthCard
                title="Sign Up"
                footerText="Already have an account?"
                footerLinkText="Log In"
                footerLinkHref="/login"
                buttonText="Sign Up"
                forgotPasswordLink={false}
                handleOnClick={handleSignUp}
            >
                <input
                    type="text"
                    placeholder="Username"
                    className="auth-input"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Email"
                    className="auth-input"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
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
