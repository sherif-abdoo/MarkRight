import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    ResponsiveContainer
} from "recharts";
import "./Stats.css"

export default function Stats({ profile }) {
    if (!profile) return null;

    const taskStats = [
        { name: "Done", value: Number(profile.doneTasks) },
        { name: "Urgent", value: Number(profile.urgentTasks) },
        { name: "Active", value: Number(profile.activeTasks) },
    ];

    const assignerStats = Object.entries(profile.assigners || {}).map(
        ([name, count]) => ({ name, value: Number(count) })
    );

    return (
        <div className="profile-stats">
            <h3 className={"profile-stats-title"}>Your Task Status</h3>
            <ResponsiveContainer width="70%" height={500}>
                <BarChart data={taskStats}>
                    <XAxis dataKey="name" />
                    <YAxis allowDecimals={false} />
                    <Tooltip />
                    <Bar dataKey="value" fill="#4f46e5" barSize={80} radius={[4, 4, 0, 0]} />
                </BarChart>
            </ResponsiveContainer>

            <h3 className={"profile-stats-title"}>Tasks Assigned To You By</h3>
            <ResponsiveContainer width="70%" height={500}>
                <BarChart data={assignerStats}>
                    <XAxis dataKey="name" />
                    <YAxis allowDecimals={false} />
                    <Tooltip />
                    <Bar dataKey="value" fill="#22c55e" barSize={80} radius={[4, 4, 0, 0]} />
                </BarChart>
            </ResponsiveContainer>
        </div>
    );
}
