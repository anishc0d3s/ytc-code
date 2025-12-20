class Book:
    def __init__(self, title, author, pages):
        self.title = title
        self.author = author
        self.pages = pages
    
    def __str__(self):
        # Called by str() and print()
        return f"'{self.title}' by {self.author}"
    
    def __repr__(self):
        # Called by repr() - should be unambiguous
        return f"Book('{self.title}', '{self.author}', {self.pages})"
    
    def __len__(self):
        # Called by len()
        return self.pages
    
    def __eq__(self, other):
        # Called by ==
        return self.title == other.title and self.author == other.author
    
    def __lt__(self, other):
        # Called by 
        return self.pages < other.pages
    
    def __add__(self, other):
        # Called by +
        return f"Collection: {self.title} & {other.title}"

book1 = Book("1984", "George Orwell", 328)
book2 = Book("Animal Farm", "George Orwell", 112)

print(book1)              # '1984' by George Orwell
print(len(book1))         # 328
print(book1 == book2)     # False
print(book1 > book2)      # True (328 > 112)
print(book1 + book2)      # Collection: 1984 & Animal Farm
