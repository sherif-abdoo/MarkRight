import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUser} from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from "react-router-dom";

export default function ProfileIcon() {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate("/profile")
    }
    return (
        <div className="popup-add-button" onClick={handleClick} >
            <FontAwesomeIcon icon={faUser}/>
        </div>
    )
}