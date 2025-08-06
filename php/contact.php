<?php
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $name = strip_tags(trim($_POST["name"]));
    $email = filter_var(trim($_POST["email"]), FILTER_SANITIZE_EMAIL);
    $subject = strip_tags(trim($_POST["subject"]));
    $message = strip_tags(trim($_POST["message"]));

    if ($name && $email && $message) {
        $to = "lucasecarpenter@gmail.com";
        $headers = "From: $name <$email>\r\nReply-To: $email\r\n";
        $fullSubject = $subject ? $subject : "New Contact Form Submission";
        $body = "Name: $name\nEmail: $email\nSubject: $subject\nMessage:\n$message";
        mail($to, $fullSubject, $body, $headers);
        echo "<h2>Thank you! Your message has been sent.</h2><a href='index.html'>Back to site</a>";
    } else {
        echo "<h2>Error: Please fill in all required fields.</h2><a href='index.html'>Back to site</a>";
    }
} else {
    header("Location: index.html");
    exit();
}
?>
