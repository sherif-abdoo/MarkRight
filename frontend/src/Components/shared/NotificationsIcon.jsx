import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBell} from "@fortawesome/free-solid-svg-icons";
import "./NotificationIcon.css"
import {useNavigate} from "react-router-dom";

export default function NotificationsIcon() {
    const navigate = useNavigate();
    const handleClick = () =>{
        navigate("/requests");
    }
    return (
        <div className="popup-add-button" onClick={handleClick} >
            <FontAwesomeIcon icon={faBell}/>
        </div>
    )
}