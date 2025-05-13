import ProfileHeader from "../Components/Profile/ProfileHeader";
import Stats from "../Components/Profile/Stats";
import {useEffect, useState} from "react";
import {authFetch} from "../utils/AuthFetch";

export default function ProfilePage(){
    const [profile, setProfile] = useState(null);
    useEffect(() => {
        const profileFetch = async () => {
            try {
                const fetchedProfile = await authFetch(`/api/v1/profile/admin`, {
                    method: "GET",
                });
                setProfile(fetchedProfile.data.profile);
            } catch (err) {
                console.error("Failed to fetch profile", err);
            }
        };

        profileFetch();
    }, []);
    return (
        <div className="profile-page">
            <ProfileHeader profile={profile} />
            <Stats profile={profile}/>
        </div>
    )
}