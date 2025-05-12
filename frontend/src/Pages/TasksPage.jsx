import CreateTask from "../Components/CreateTask";
import TasksContainer from "../Components/TasksContainer";

export default function TasksPage(){
    return(
        <div className="tasks-page">
            <CreateTask/>
            <TasksContainer/>
        </div>
    )
}