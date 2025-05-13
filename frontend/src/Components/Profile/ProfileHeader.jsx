import "./ProfileHeader.css"
import profileImg from "../../assets/profile.jpg"
export default function ProfileHeader({profile}) {
    if (!profile) return null;
    return(
        <header className="profile-header">
            <img src = {profileImg} alt={"Profile"} className="profile-img"/>
            <div className="profile-header-info">
                <div className="profile-header-info-name">{profile.username}</div>
                <div className="profile-header-info-name">Total tasks : {profile.totalTasks}</div>
            </div>
        </header>
    )
}