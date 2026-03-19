// ============================================
// MONGODB SAMPLE DATA INSERTION SCRIPT
// ============================================
// Run this in MongoDB Atlas Web Console or MongoDB Compass Terminal
// Copy and paste all commands at once

// ============================================
// 1. INSERT AppointmentStatus
// ============================================
db.AppointmentStatus.deleteMany({});  // Clear existing data

db.AppointmentStatus.insertMany([
  {
    _id: ObjectId("65917db3c01b79393720710c"),
    status: "completed",
    description: "Appointment completed successfully",
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("65917dd1c01b79393720710d"),
    status: "pending",
    description: "Appointment is pending confirmation",
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("65917ddbc01b79393720710e"),
    status: "confirmed",
    description: "Appointment is confirmed",
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("65917de5c01b79393720710f"),
    status: "cancelled",
    description: "Appointment was cancelled",
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  }
]);

print("✅ AppointmentStatus inserted: " + db.AppointmentStatus.countDocuments());

// ============================================
// 2. INSERT Users
// ============================================
db.Users.deleteMany({});

db.Users.insertMany([
  {
    _id: ObjectId("607f1f77bcf86cd799439011"),
    firstName: "Aloka",
    lastName: "Lakruwan",
    username: "aloka_salon",
    nicPassport: "123456789V",
    email: "aloka@beautysalon.com",
    contactNo: "0701234567",
    password: "$2a$10$slYQmyNdGzin7olVN3p5Ae83jVfFCPbQLLa2o6Y3cWoGV5jzPaM9i",  // hashed: password123
    userGroup: ObjectId("607f1f77bcf86cd799439021"),
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439012"),
    firstName: "Priya",
    lastName: "Silva",
    username: "priya_customer",
    nicPassport: "987654321V",
    email: "priya@email.com",
    contactNo: "0759876543",
    password: "$2a$10$slYQmyNdGzin7olVN3p5Ae83jVfFCPbQLLa2o6Y3cWoGV5jzPaM9i",  // hashed: password123
    userGroup: ObjectId("607f1f77bcf86cd799439022"),
    created_at: new Date("2024-01-02"),
    updated_at: new Date("2024-01-02")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439013"),
    firstName: "Sanjaya",
    lastName: "Perera",
    username: "sanjaya_doctor",
    nicPassport: "555666777V",
    email: "sanjaya@clinic.com",
    contactNo: "0771111111",
    password: "$2a$10$slYQmyNdGzin7olVN3p5Ae83jVfFCPbQLLa2o6Y3cWoGV5jzPaM9i",  // hashed: password123
    userGroup: ObjectId("607f1f77bcf86cd799439023"),
    created_at: new Date("2024-01-03"),
    updated_at: new Date("2024-01-03")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439014"),
    firstName: "Nishara",
    lastName: "Jayawardena",
    username: "nishara_staff",
    nicPassport: "444333222V",
    email: "nishara@beautysalon.com",
    contactNo: "0782222222",
    password: "$2a$10$slYQmyNdGzin7olVN3p5Ae83jVfFCPbQLLa2o6Y3cWoGV5jzPaM9i",  // hashed: password123
    userGroup: ObjectId("607f1f77bcf86cd799439024"),
    created_at: new Date("2024-01-04"),
    updated_at: new Date("2024-01-04")
  }
]);

print("✅ Users inserted: " + db.Users.countDocuments());

// ============================================
// 3. INSERT ServiceCategory
// ============================================
db.ServiceCategory.deleteMany({});

db.ServiceCategory.insertMany([
  {
    _id: ObjectId("607f1f77bcf86cd799439031"),
    name: "Hair Services",
    description: "Hair cutting, coloring, and styling services",
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439032"),
    name: "Skin Care",
    description: "Facial treatments and skin care services",
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439033"),
    name: "Nail Services",
    description: "Manicure, pedicure, and nail art",
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439034"),
    name: "Body Massage",
    description: "Relaxation and therapeutic massage services",
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  }
]);

print("✅ ServiceCategory inserted: " + db.ServiceCategory.countDocuments());

// ============================================
// 4. INSERT Services
// ============================================
db.services.deleteMany({});

db.services.insertMany([
  {
    _id: ObjectId("607f1f77bcf86cd799439041"),
    name: "Basic Haircut",
    description: "Professional haircut with styling",
    price: 2500,
    duration: 30,
    category: ObjectId("607f1f77bcf86cd799439031"),
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439042"),
    name: "Hair Coloring",
    description: "Full hair coloring with premium products",
    price: 5000,
    duration: 120,
    category: ObjectId("607f1f77bcf86cd799439031"),
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439043"),
    name: "Facial Treatment",
    description: "Deep cleansing and hydrating facial",
    price: 3500,
    duration: 60,
    category: ObjectId("607f1f77bcf86cd799439032"),
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439044"),
    name: "Manicure",
    description: "Complete manicure with nail polish",
    price: 1500,
    duration: 45,
    category: ObjectId("607f1f77bcf86cd799439033"),
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439045"),
    name: "Full Body Massage",
    description: "Relaxing full body therapeutic massage",
    price: 4000,
    duration: 90,
    category: ObjectId("607f1f77bcf86cd799439034"),
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  }
]);

print("✅ Services inserted: " + db.services.countDocuments());

// ============================================
// 5. INSERT Appointments
// ============================================
db.appointments.deleteMany({});

db.appointments.insertMany([
  {
    _id: ObjectId("607f1f77bcf86cd799439051"),
    user_id: ObjectId("607f1f77bcf86cd799439012"),
    service_id: ObjectId("607f1f77bcf86cd799439041"),
    date: new Date("2024-02-15"),
    startTime: "10:00",
    endTime: "10:30",
    status: ObjectId("65917ddbc01b79393720710e"),  // confirmed
    note: "First time customer",
    created_at: new Date("2024-01-20"),
    updated_at: new Date("2024-01-20")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439052"),
    user_id: ObjectId("607f1f77bcf86cd799439012"),
    service_id: ObjectId("607f1f77bcf86cd799439043"),
    date: new Date("2024-02-16"),
    startTime: "14:00",
    endTime: "15:00",
    status: ObjectId("65917dd1c01b79393720710d"),  // pending
    note: "Sensitive skin - please use mild products",
    created_at: new Date("2024-01-20"),
    updated_at: new Date("2024-01-20")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439053"),
    user_id: ObjectId("607f1f77bcf86cd799439013"),
    service_id: ObjectId("607f1f77bcf86cd799439045"),
    date: new Date("2024-02-17"),
    startTime: "16:00",
    endTime: "17:30",
    status: ObjectId("65917db3c01b79393720710c"),  // completed
    note: "Regular customer",
    created_at: new Date("2024-01-18"),
    updated_at: new Date("2024-02-17")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439054"),
    user_id: ObjectId("607f1f77bcf86cd799439012"),
    service_id: ObjectId("607f1f77bcf86cd799439044"),
    date: new Date("2024-02-20"),
    startTime: "11:00",
    endTime: "11:45",
    status: ObjectId("65917de5c01b79393720710f"),  // cancelled
    note: "Cancelled due to emergency",
    created_at: new Date("2024-01-19"),
    updated_at: new Date("2024-02-19")
  }
]);

print("✅ Appointments inserted: " + db.appointments.countDocuments());

// ============================================
// 6. INSERT Reviews
// ============================================
db.reviews.deleteMany({});

db.reviews.insertMany([
  {
    _id: ObjectId("607f1f77bcf86cd799439061"),
    user_id: ObjectId("607f1f77bcf86cd799439012"),
    service_id: ObjectId("607f1f77bcf86cd799439041"),
    appointment_id: ObjectId("607f1f77bcf86cd799439051"),
    rating: 5,
    review: "Excellent service! Very professional and friendly staff.",
    created_at: new Date("2024-02-15"),
    updated_at: new Date("2024-02-15")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439062"),
    user_id: ObjectId("607f1f77bcf86cd799439013"),
    service_id: ObjectId("607f1f77bcf86cd799439045"),
    appointment_id: ObjectId("607f1f77bcf86cd799439053"),
    rating: 4,
    review: "Great massage, very relaxing. Will visit again.",
    created_at: new Date("2024-02-17"),
    updated_at: new Date("2024-02-17")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439063"),
    user_id: ObjectId("607f1f77bcf86cd799439014"),
    service_id: ObjectId("607f1f77bcf86cd799439043"),
    appointment_id: ObjectId("607f1f77bcf86cd799439052"),
    rating: 5,
    review: "Perfect facial treatment! Skin feels amazing.",
    created_at: new Date("2024-02-16"),
    updated_at: new Date("2024-02-16")
  }
]);

print("✅ Reviews inserted: " + db.reviews.countDocuments());

// ============================================
// 7. INSERT Rankings
// ============================================
db.rankings.deleteMany({});

db.rankings.insertMany([
  {
    _id: ObjectId("607f1f77bcf86cd799439071"),
    service_id: ObjectId("607f1f77bcf86cd799439041"),
    average_rating: 5.0,
    total_reviews: 1,
    created_at: new Date("2024-02-15"),
    updated_at: new Date("2024-02-15")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439072"),
    service_id: ObjectId("607f1f77bcf86cd799439043"),
    average_rating: 5.0,
    total_reviews: 1,
    created_at: new Date("2024-02-16"),
    updated_at: new Date("2024-02-16")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439073"),
    service_id: ObjectId("607f1f77bcf86cd799439045"),
    average_rating: 4.0,
    total_reviews: 1,
    created_at: new Date("2024-02-17"),
    updated_at: new Date("2024-02-17")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439074"),
    service_id: ObjectId("607f1f77bcf86cd799439042"),
    average_rating: 0.0,
    total_reviews: 0,
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  },
  {
    _id: ObjectId("607f1f77bcf86cd799439075"),
    service_id: ObjectId("607f1f77bcf86cd799439044"),
    average_rating: 0.0,
    total_reviews: 0,
    created_at: new Date("2024-01-01"),
    updated_at: new Date("2024-01-01")
  }
]);

print("✅ Rankings inserted: " + db.rankings.countDocuments());

// ============================================
// VERIFICATION - Run these to verify data
// ============================================
print("\n📊 DATA SUMMARY:");
print("AppointmentStatus: " + db.AppointmentStatus.countDocuments() + " documents");
print("Users: " + db.Users.countDocuments() + " documents");
print("ServiceCategory: " + db.ServiceCategory.countDocuments() + " documents");
print("Services: " + db.services.countDocuments() + " documents");
print("Appointments: " + db.appointments.countDocuments() + " documents");
print("Reviews: " + db.reviews.countDocuments() + " documents");
print("Rankings: " + db.rankings.countDocuments() + " documents");
print("\n✅ All data inserted successfully!");
