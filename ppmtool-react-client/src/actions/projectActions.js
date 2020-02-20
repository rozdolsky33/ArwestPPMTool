import axios from 'axios';
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT } from './types';

export const createProject = (project, history) => async dispatch => {
  try {
    const res = await axios.post('http://localhost:8080/api/project', project);
    history.push('/dashboard');
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const getProjects = () => async dispatch => {
  const res = await axios.get('http://localhost:8080/api/project/all');
  dispatch({
    type: GET_PROJECTS,
    payload: res.data
  });
};

export const getProject = (id, history) => async dispatch => {
  const res = await axios.get(`http://localhost:8080/api/project/${id}`);
  dispatch({
    type: GET_PROJECT,
    payload: res.data
  });
};
export const updateProject = (id, project, history) => async dispatch => {
  history.push('/dashboard');
  try {
    const res = await axios.get(`http://localhost:8080/api/project/${id}`);
    history.push('/dashboard');
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};
