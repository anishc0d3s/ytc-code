class Library:
    def __init__(self, name):
        self.name = name
        self.books = []
        self.members = []
    
    def add_book(self, book):
        self.books.append(book)
        print(f"Added '{book.title}' to {self.name}") ## self.name is the library name
    
    def register_member(self, member):
        self.members.append(member)
        print(f"Registered {member.name} as a member")
    
    def lend_book(self, book_title, member):
        for book in self.books:
            ## Check if book is available in the library and available
            if book.title == book_title and book.available:
                # update book's availability
                book.available = False
                # update member's borrowed books
                member.borrowed_books.append(book)
                return f"{member.name} borrowed '{book.title}'"
        return "Book not available"

class LibraryBook:
    def __init__(self, title, author, isbn):
        self.title = title
        self.author = author
        self.isbn = isbn
        self.available = True

class Member:
    def __init__(self, name, member_id):
        self.name = name
        self.member_id = member_id
        self.borrowed_books = []
    
    def list_borrowed_books(self):
        if not self.borrowed_books:
            return f"{self.name} has no borrowed books"
        titles = [book.title for book in self.borrowed_books]
        return f"{self.name}'s books: {', '.join(titles)}"

# Usage
library = Library("City Library")

book1 = LibraryBook("Python Crash Course", "Eric Matthes", "123456")
book2 = LibraryBook("Clean Code", "Robert Martin", "789012")

member = Member("Alice", "M001")

library.add_book(book1)
library.add_book(book2)
library.register_member(member)

print(library.lend_book("Python Crash Course", member))
print(member.list_borrowed_books())
