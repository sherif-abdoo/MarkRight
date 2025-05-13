import "./ProfileHeader.css"
export default function ProfileHeader() {
    return(
        <header className="profile-header">
            <img src = "../../assets/logo192.png" alt={"Profile"} />
            <div className="profile-header-info">
                <div className="profile-header-info-name">Sherifabdo</div>
                <div className="profile-header-info-name">Total tasks : 15</div>
            </div>
        </header>
    )
}