import ProfileHeader from "../Components/Profile/ProfileHeader";
import Stats from "../Components/Profile/Stats";
import {useEffect, useState} from "react";
import {authFetch} from "../utils/AuthFetch";
import {useParams} from "react-router-dom";

export default function ProfilePage(){
    const [profile, setProfile] = useState(null);
    const {username} = useParams();
    useEffect(() => {
        const profileFetch = async () => {
            try {
                const fetchedProfile = await authFetch(`/api/v1/profile/${username}`, {
                    method: "GET",
                });
                setProfile(fetchedProfile.data.profile);
            } catch (err) {
                console.error("Failed to fetch profile", err);
            }
        };

        profileFetch();
    }, [username]);
    return (
        <div className="profile-page">
            <ProfileHeader profile={profile} />
            <Stats profile={profile}/>
        </div>
    )
}