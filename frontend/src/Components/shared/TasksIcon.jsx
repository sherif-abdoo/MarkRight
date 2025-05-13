import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheckDouble} from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from "react-router-dom";

export default function TasksIcon() {
    const navigate = useNavigate();
    const handleClick = () =>{
        navigate("/");
    }
    return (
        <div className="popup-add-button" onClick={handleClick} >
            <FontAwesomeIcon icon={faCheckDouble} />
        </div>
    )
}