function addActivityItem(activity) {
    const feed = document.getElementById('activityFeed');
    const item = document.createElement('div');
    item.className = 'activity-item bg-white rounded-3 shadow-sm p-3 mb-3';
    item.innerHTML = `
        <div class="d-flex align-items-center">
            <img src="${activity.avatar}" class="rounded-circle me-3" width="40" height="40">
            <div>
                <p class="mb-1"><strong>${activity.user}</strong> ${activity.action}</p>
                <small class="text-muted">${activity.time}</small>
            </div>
        </div>
    `;
    feed.prepend(item);

    // Remove old items if there are too many
    if (feed.children.length > 5) {
        feed.removeChild(feed.lastChild);
    }
}

// Simulate live activities
setInterval(() => {
    const activities = [
        {user: 'Nguyễn Văn A', action: 'vừa trả lời câu hỏi về React Hooks', time: 'Vừa xong', avatar: 'avatar1.jpg'},
        {user: 'Trần Thị B', action: 'đã đặt một câu hỏi mới về Docker', time: 'Vừa xong', avatar: 'avatar2.jpg'},
        {user: 'Lê Văn C', action: 'vừa nhận được badge mới', time: 'Vừa xong', avatar: 'avatar3.jpg'}
    ];

    const randomActivity = activities[Math.floor(Math.random() * activities.length)];
    addActivityItem(randomActivity);
}, 5000);