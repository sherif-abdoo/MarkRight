import CreateTask from "../Components/shared/CreateTask";
import TasksContainer from "../Components/tasks/TasksContainer";
import VerticalNavbar from "../Components/shared/VerticalNavbar";

export default function TasksPage(){
    return(
        <div className="tasks-page">
            <VerticalNavbar/>
            <TasksContainer all = {true}/>
        </div>
    )
}