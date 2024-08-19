import React, { useState, useEffect } from "react";

const API_BASE_URL = "http://localhost:8081";
const today = new Date().toISOString().split('T')[0]; 
const initData = {
  teacherId: "",
  firstName: "Nguyen Van",
  lastName: "A",
  teacherTypeId: 1, 
  educationLevelId: 1, 
  image: "asd",
  baseSalary: 0,
  startDate: today, 
};

const TeacherManagement = () => {
  const [teachers, setTeachers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [newTeacher, setNewTeacher] = useState(initData);
  const [isEditing, setIsEditing] = useState(false);
  const [educationLevels, setEducationLevels] = useState([]);
  const [teacherTypes, setTeacherTypes] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [teacherRes, teacherTypesRes, educationLevelsRes] = await Promise.all([
          fetch(`${API_BASE_URL}/teachers`),
          fetch(`${API_BASE_URL}/teacherTypes`),
          fetch(`${API_BASE_URL}/educationLevels`)
        ]);

        if (!teacherRes.ok || !teacherTypesRes.ok || !educationLevelsRes.ok) {
          throw new Error("Failed to fetch data");
        }

        const teachersData = await teacherRes.json();
        const teacherTypesData = await teacherTypesRes.json();
        const educationLevelsData = await educationLevelsRes.json();

        setTeachers(teachersData);
        setTeacherTypes(teacherTypesData);
        setEducationLevels(educationLevelsData);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const handleCreateOrUpdate = async () => {
    try {
      const method = isEditing ? "PUT" : "POST";
      if (method === "POST") {
        // Check if teacherId already exists
        const checkResponse = await fetch(`${API_BASE_URL}/teachers/${newTeacher.teacherId}`);
        
        if (checkResponse.ok) {
          // If teacher exists, show alert and return
          alert("Mã giáo viên đã tồn tại");
          return;
        }
      }
      const url = isEditing
        ? `${API_BASE_URL}/teachers/${newTeacher.teacherId}`
        : `${API_BASE_URL}/teachers`;

      const response = await fetch(url, {
        method: method,
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newTeacher),
      });

      if (!response.ok) throw new Error("Failed to save teacher");

      const savedTeacher = await response.json();
      
      if (isEditing) {
        setTeachers(
          teachers.map((teacher) =>
            teacher.teacherId === newTeacher.teacherId ? savedTeacher : teacher
          )
        );
        setIsEditing(false);
      } else {
        setTeachers([...teachers, savedTeacher]);
      }

      resetForm();
    } catch (error) {
      setError(error.message);
    }
  };

  const handleEdit = (teacher) => {
    setIsEditing(true);
    console.log(teacher)
    teacher.educationLevelId = teacher.educationLevel.id
    teacher.teacherTypeId = teacher.teacherType.id
    setNewTeacher(teacher);
  };

  const handleDelete = async (id) => {
    try {
      const response = await fetch(`${API_BASE_URL}/teachers/${id}`, {
        method: "DELETE",
      });

      if (!response.ok) throw new Error("Failed to delete teacher");

      setTeachers(teachers.filter((teacher) => teacher.teacherId !== id));
    } catch (error) {
      setError(error.message);
    }
  };

  const resetForm = () => {
    setNewTeacher(initData);
    setIsEditing(false);
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h2 className="text-center text-danger my-2">Quản lý giảng viên</h2>
      <div className="w-50 mx-auto border p-4">
        <div className="text-start">
          <label className="mt-2">Mã giáo viên</label>
          <input
            className="form-control"
            type="text"
            required
            placeholder="Mã..."
            value={newTeacher.teacherId}
            onChange={(e) =>
              setNewTeacher({ ...newTeacher, teacherId: e.target.value })
            }
            disabled={isEditing}
          />
        </div>
        <div className="text-start">
          <label className="mt-2">Họ</label>
          <input
            className="form-control"
            type="text"
            required
            placeholder="Họ..."
            value={newTeacher.firstName}
            onChange={(e) =>
              setNewTeacher({ ...newTeacher, firstName: e.target.value })
            }
          />
        </div>
        <div className="text-start">
          <label className="mt-2">Tên</label>
          <input
            className="form-control"
            type="text"
            required
            placeholder="Tên..."
            value={newTeacher.lastName}
            onChange={(e) =>
              setNewTeacher({ ...newTeacher, lastName: e.target.value })
            }
          />
        </div>
        <div className="text-start">
          <label className="mt-2">Hình ảnh</label>
          <input
            className="form-control"
            type="text"
            required
            placeholder="Url hình..."
            value={newTeacher.image}
            onChange={(e) =>
              setNewTeacher({ ...newTeacher, image: e.target.value })
            }
          />
        </div>
        <div className="text-start">
          <label className="mt-2">Lương</label>
          <input
            className="form-control"
            type="number"
            required
            min={100000}
            placeholder="Base Salary"
            value={newTeacher.baseSalary}
            onChange={(e) =>
              setNewTeacher({
                ...newTeacher,
                baseSalary: parseInt(e.target.value, 10),
              })
            }
          />
        </div>
        <div className="text-start">
          <label className="mt-2">Ngày bắt đầu</label>
          <input
            className="form-control"
            type="date"
            placeholder="Start Date"
            value={newTeacher.startDate}
            onChange={(e) =>
              setNewTeacher({ ...newTeacher, startDate: e.target.value })
            }
          />
        </div>
        <div className="text-start">
          <label className="mt-2">Trình độ</label>
          <select
            className="form-control"
            value={newTeacher.educationLevelId}
            onChange={(e) =>
              setNewTeacher({
                ...newTeacher,
                educationLevelId: parseInt(e.target.value, 10),
              })
            }
          >
            {educationLevels.map((level) => (
              <option key={level.id} value={level.id}>
                {level.levelName}
              </option>
            ))}
          </select>
        </div>
        <div className="text-start">
          <label className="mt-2">Loại</label>
          <select
            className="form-control"
            value={newTeacher.teacherTypeId}
            onChange={(e) =>
              setNewTeacher({
                ...newTeacher,
                teacherTypeId: parseInt(e.target.value, 10),
              })
            }
          >
            {teacherTypes.map((type) => (
              <option key={type.id} value={type.id}>
                {type.typeName}
              </option>
            ))}
          </select>
        </div>
        <div className="text-center mt-4">
          <button
            className="btn btn-success"
            onClick={handleCreateOrUpdate}
          >
            {isEditing ? "Cập nhật" : "Thêm"}
          </button>
          {isEditing && (
            <button className="btn btn-secondary ms-2" onClick={resetForm}>
              Hủy
            </button>
          )}
        </div>
      </div>

      <div className="border p-4 mx-auto my-2" style={{width: "70%"}}>
        <h3 className="text-center text-danger">Danh sách</h3>
        <table className="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Mã</th>
              <th scope="col">Họ và tên</th>
              <th scope="col">Trình độ</th>
              <th scope="col">Loại</th>
              <th scope="col">Lương</th>
              <th scope="col">Ngày bắt đầu</th>
              <th scope="col">Tác vụ</th>
            </tr>
          </thead>
          <tbody>
            {teachers.map((teacher, index) => (
              <tr key={teacher.teacherId}>
                <th scope="row">{index + 1}</th>
                <td>{teacher.teacherId}</td>
                <td>{`${teacher.firstName} ${teacher.lastName}`}</td>
                <td>{teacher.educationLevel?.levelName}</td>
                <td>{teacher.teacherType?.typeName}</td>
                <td>{teacher.baseSalary}</td>
                <td>{teacher.startDate}</td>
                <td>
                  <button
                    className="btn btn-primary me-2"
                    onClick={() => handleEdit(teacher)}
                  >
                    Sửa
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={() => handleDelete(teacher.teacherId)}
                  >
                    Xóa
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default TeacherManagement;
