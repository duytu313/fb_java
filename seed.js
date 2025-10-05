const axios = require("axios");

const BASE = "http://localhost:3000";

async function seed() {
  try {
    // 1️⃣ Tạo users
    const usersData = [
      { username: "userA", email: "userA@example.com", password: "123456" },
      { username: "userB", email: "userB@example.com", password: "123456" },
      { username: "userC", email: "userC@example.com", password: "123456" },
    ];

    const users = [];
    for (const u of usersData) {
      const res = await axios.post(`${BASE}/register`, u);
      console.log(res.data.message, "->", res.data.userId);
      users.push({ ...u, _id: res.data.userId });
    }

    // 2️⃣ Tạo posts
    const postsData = [
      { userId: users[0]._id, content: "Xin chào, đây là post của userA" },
      { userId: users[1]._id, content: "Post test của userB" },
      { userId: users[2]._id, content: "Chào mọi người, mình là userC" },
    ];

    const posts = [];
    for (const p of postsData) {
      const res = await axios.post(`${BASE}/createPost`, p);
      console.log(res.data.message, "->", res.data.post._id);
      posts.push(res.data.post);
    }

    // 3️⃣ Like chéo
    const likes = [
      { postId: posts[0]._id, userId: users[1]._id },
      { postId: posts[0]._id, userId: users[2]._id },
      { postId: posts[1]._id, userId: users[0]._id },
    ];

    for (const l of likes) {
      const res = await axios.post(`${BASE}/likePost`, l);
      console.log(res.data.message);
    }

    // 4️⃣ Comment chéo
    const comments = [
      { postId: posts[0]._id, userId: users[1]._id, comment: "Nice post!" },
      { postId: posts[1]._id, userId: users[2]._id, comment: "Tuyệt vời" },
      { postId: posts[2]._id, userId: users[0]._id, comment: "Chào bạn C!" },
    ];

    for (const c of comments) {
      const res = await axios.post(`${BASE}/commentPost`, c);
      console.log(res.data.message);
    }

    console.log("✅ Seed data xong!");
  } catch (err) {
    console.error(err.response?.data || err.message);
  }
}

seed();
